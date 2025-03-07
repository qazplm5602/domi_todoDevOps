import { Suspense } from "react";
import NowEmptyBox from "../../components/nowSection/NowEmptyBox";
import NowTodoSection from "../../components/nowSection/NowTodoSection";
import { getCurrentUser } from "../../hooks/loginUser";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: 'Home'
}

export default async function Page() {
    const user = await getCurrentUser();
    
    if (!user)
        return <main className="container-lg">
            <h1 className="h3" style={{ marginTop: 30 }}>로그인을 해야 서비스를 이용할 수 있습니다.</h1>
        </main>

    return <main className="container-lg">
        <div className="container text-center">
            <div className="row">
                <NowTodoSection title={<h2>오늘 할일</h2>} uri="/today" className="col" emptyDoc={<NowEmptyBox text="오늘은 할일이 없네요."/>} />
                <NowTodoSection title={<h2>지난 일</h2>} uri="/prev" className="col" />
            </div>
        </div>

        <NowTodoSection title={<h2>할일 리스트</h2>} uri="/all" />
    </main>;
}