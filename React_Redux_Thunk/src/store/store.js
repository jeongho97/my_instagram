import { configureStore, combineReducers } from "@reduxjs/toolkit";

import users from "./users";
import posts from "./posts";
import follows from "./follows";
const reducer = combineReducers({
  //루트 reducer들
  users,
  posts,
  follows,
});
export default configureStore({
  reducer,
  devTools: true,
  middleware: (getDefaultMiddleware) => [...getDefaultMiddleware()],
});
