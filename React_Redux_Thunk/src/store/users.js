import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Users } from "../data/User";
import {
  checkId,
  getUserById,
  getUserByKey,
  getUserByUserId,
  loginApi,
  loginCheckApi,
  logoutApi,
  postUser,
  putUsers,
} from "./usersApi";
const initialState = {
  users: Users,
  myId: localStorage.getItem("token"), //브라우저상에 저장된 데이터 가져오기
  isLogin: localStorage.getItem("token") === undefined ? true : false,
  me: {},
};

//액션 이름 정의(액션 이름은 고유해야함)
const CHECK_ID = "CHECK_ID";
const LOGIN_CHECK = "LOGIN_CHECK";
const LOGIN = "LOGIN";
const INSERT_USER = "INSERT_USER";
const SELECT_USER_BY_ID = "SELECT_USER_BY_ID";
const SELECT_USER_BY_USERID = "SELECT_USER_BY_USERID";
const LOGOUT = "LOGOUT";
const UPDATE_USERS = "UPDATE_USERS";
const SELECT_USER_BY_KEY = "SELECT_USER_BY_KEY";

//redux-thunk는 리덕스에서 비동기 작업을 처리할 때 사용하는 미들웨어이다.
//(이 미들웨어를 사용하면 액션 객체가 아닌 함수를 디스패치 할 수 있다)
//Redux Toolkit에는 내부적으로 thunk를 내장하고 있어서, 다른 미들웨어를 사용하지 않고도 비동기 처리를 할 수 있다.
//물론 Redux Toolkit의 비동기 처리 기능을 사용하지 않고, 컴포넌트 내부의 useEffect()에서 API 호출을 하는 것도 가능하다. 실제로 지금까지 그렇게 해왔다.
//다만, Redux Toolkit의 비동기 기능을 사용하면, 컴포넌트 외부에서 비동기 처리를 할 수 있기 때문에 관심사 분리가 가능하다는 장점이 있다.

//액션 구현
export const getCheckId = createAsyncThunk(
  // string action type value: 이 값에 따라 pending, fulfilled, rejected가 붙은 액션 타입이 생성된다.
  CHECK_ID, //액션 이름
  // payloadCreator callback: 비동기 로직의 결과를 포함하고 있는 프로미스를 반환하는 비동기 함수
  async (userId, thunkAPI) => {
    //thunkAPI는  thunk함수에 전달되는 모든 매개변수와 추가 옵션을 포함하는 객체
    const { users } = thunkAPI.getState().users; //thunkAPI는 State값 들고옴 users는 State에 있는 users 결국 data에 User.js에 저장시킨 데이터들임 DB를 구축안해서 해놓은것
    console.log(users);
    return await checkId(users, userId);
  }
);
export const loginCheck = createAsyncThunk(
  LOGIN_CHECK,
  async (payload, thunkAPI) => {
    const { users, myId } = thunkAPI.getState().users;
    if (myId) {
      const me = await loginCheckApi(users, Number(myId));
      return me;
    } else if (myId === 0 || myId === "0") {
      const me = await loginCheckApi(users, Number(myId));
      return me;
    }
    return;
  }
);
export const login = createAsyncThunk(LOGIN, async (user, thunkAPI) => {
  const { users } = thunkAPI.getState().users;
  const isLogin = await loginApi(users, user);
  return isLogin;
});
export const insertUser = createAsyncThunk(
  INSERT_USER,
  async (user, thunkAPI) => {
    const { users } = thunkAPI.getState().users;
    const newUser = await postUser(users, user);
    return newUser;
  }
);
export const selectUserById = createAsyncThunk(
  SELECT_USER_BY_ID,
  async (id, thunkAPI) => {
    const { users } = thunkAPI.getState().users;
    const newUser = await getUserById(users, id);
    return newUser;
  }
);

export const selectUserByUserId = createAsyncThunk(
  SELECT_USER_BY_USERID,
  async (userId, thunkAPI) => {
    const { users } = thunkAPI.getState().users;
    const newUser = await getUserByUserId(users, userId);
    return newUser;
  }
);

export const logout = createAsyncThunk(LOGOUT, async (payload, thunkAPI) => {
  const { myId } = thunkAPI.getState().users;
  const isLogout = await logoutApi(myId);
  return isLogout;
});
export const updateUsers = createAsyncThunk(
  UPDATE_USERS,
  async (user, thunkAPI) => {
    const { myId, users } = thunkAPI.getState().users;
    const newUsers = await putUsers(users, user, myId);
    return { newUsers, user };
  }
);
export const selectUserByKey = createAsyncThunk(
  SELECT_USER_BY_KEY,
  async (key, thunkAPI) => {
    const { users } = thunkAPI.getState().users;
    const reg = new RegExp(key, "g"); //g:전체에서 key가 있는지 검사하여 반환.
    const newUsers = await getUserByKey(users, reg);

    return newUsers.id;
  }
);

//reducer 부분
export const usersSlice = createSlice({
  name: "users", //해당 모듈의 이름 , 액션 타입 문자열의 prefix로 자동으로 들어갑니다.
  initialState, //모듈의 초기값
  reducers: {}, //리듀서 작성, 해당 리듀서의 키값으로 액션함수가 자동으로 생성
  extraReducers: (builder) => {
    //액션함수가 자동으로 생성되지 않는 별도의 액션함수가 존재하는 리듀서를 정의합니다. (선택 옵션이며 thunk에 대한 reducer를 작성하는 공간입니다.,thunk의 경우 액션함수를 따로 만들어 줘야 합니다.)
    //peding:처리전 fulfilled: 완료 rejected:에러처리
    //액션들에 대한 리듀서를 추가하는 부분, 필요한 state를 처리하는 부분
    builder
      .addCase(loginCheck.fulfilled, (state, { payload }) => {
        // payload는 액션에대한 정보
        if (payload) {
          return { ...state, isLogin: true, me: payload };
        } else {
          return { ...state, isLogin: false };
        }
      })
      .addCase(login.fulfilled, (state, { payload }) => {
        if (payload.isLogin) {
          localStorage.setItem("token", payload.user.token);
          return {
            ...state,
            isLogin: payload.login,
            me: payload.user,
          };
        } else {
          return { ...state, isLogin: false };
        }
      })
      .addCase(insertUser.fulfilled, (state, { payload }) => {
        return { ...state, users: payload };
      })
      .addCase(logout.fulfilled, (state, { payload }) => {
        localStorage.removeItem("token");
        return { ...state, isLogin: false, me: {}, myId: "" };
      })
      .addCase(updateUsers.fulfilled, (state, { payload }) => {
        const { newUsers, user } = payload;

        return { ...state, me: { ...state.me, ...user }, users: newUsers };
      });
  },
});

export default usersSlice.reducer;
