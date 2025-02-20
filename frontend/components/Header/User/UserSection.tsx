import Link from "next/link";
import { getCurrentUser } from "../../../hooks/loginUser";
import style from '../header.module.scss';
import LogoutBtn from "./LogoutBtn";

export default async function LoginUserSection() {
    const user = await getCurrentUser();

    return <section className={style.user}>
        <div className={style.name}>{user.name} 환영합니다.</div>
        <LogoutBtn />
        <Link href="/create" className="btn btn-warning">생성</Link>
    </section>
}