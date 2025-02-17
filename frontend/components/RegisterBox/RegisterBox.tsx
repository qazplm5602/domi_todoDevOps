import ShadowBox from "../ShadowBox/ShadowBox";
import loginStyle from '../LoginBox/loginBox.module.scss';
import { Button } from "react-bootstrap";
import LoginInput from "../LoginBox/Input";

export default function RegisterBox() {
    return <ShadowBox className={loginStyle.box}>
        <h1 className={loginStyle.title}>회원가입</h1>

        <form>
            
        <LoginInput icon="✉️" placeholder='이메일' type='email' name='email' />
        <LoginInput icon="🔑" placeholder='비밀번호' type='password' name='password' />
        <LoginInput icon="🔑" placeholder='비밀번호 재입력' type='password' name='passwordre' />
        <LoginInput icon="🏷️" placeholder='이름' type='text' name='username' />

        {/* <div className={`text-danger ${loginStyle.err}`}>비밀번호가 일치하지 않습니다.</div> */}

        <Button className={`${loginStyle.btn} btn-success`} type='submit'>가입</Button>
        </form>
    </ShadowBox>;
}