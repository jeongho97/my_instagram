import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Alert, Button, Col, Container, Form, Input, Row } from "reactstrap";
import { login } from "../../store/users";
import AuthRouter from "../AuthRouter";
import "./Login.css";
const BootstrapLogin = () => {
  const dispatch = useDispatch();
  const [isFail, setIsFail] = useState(false);
  const [user, setUser] = useState({
    id: "",
    password: "",
  });
  const onChangeHandler = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value }); //...user : props의 값들을 한번에 담아 보낸다
  };
  const navigate = useNavigate(); //페이지를 리다이렉트 할때 사용
  const onSubmitLogin = async (e) => {
    e.preventDefault(); //a 태그나 submit 태그는 누르게 되면 href 를 통해 이동하거나 , 창이 새로고침하여 실행됩니다.
    // preventDefault 를 통해 이러한 동작을 막아줄 수 있습니다.
    const { isLogin } = await dispatch(login(user)).unwrap(); //unwrap 리턴값을 받으려고 사용 / 오류처리에도 사용가능

    if (isLogin) {
      navigate("/");
    } else {
      setIsFail(true);
      setTimeout(() => closeAlert(), 3000);
    }
  };

  const closeAlert = () => {
    setIsFail(false);
  };
  return (
    <div className="LoginPage">
      <Container className="bg-light border">
        <Row style={{ rowGap: "1em", padding: "3em" }}>
          <Col xl={12}>
            <img
              src="https://www.instagram.com/static/images/web/logged_out_wordmark-2x.png/d2529dbef8ed.png"
              alt="Logo"
            ></img>
          </Col>

          <Col xl={12}>
            <Form onSubmit={onSubmitLogin} className="LoginForm">
              {isFail ? (
                <Alert color="warning" toggle={() => closeAlert()}>
                  아이디 또는 비밀번호가 틀렸습니다.
                </Alert>
              ) : null}
              <Input
                type="text"
                placeholder="ID"
                name="id"
                onChange={(e) => onChangeHandler(e)}
              ></Input>
              <Input
                type="password"
                placeholder="password"
                name="password"
                onChange={(e) => onChangeHandler(e)}
              ></Input>
              <Button type={"submit"} color="primary" block>
                로그인
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
      <Container className="bg-light border">
        <Row style={{ padding: "1em", textAlign: "center" }}>
          <p>
            계정이 없으신가요? <a href="/join">가입하기</a>
          </p>
        </Row>
      </Container>
      <AuthRouter></AuthRouter>
    </div>
  );
};
export default BootstrapLogin;
