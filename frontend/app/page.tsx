import NowEmptyBox from "./nowSection/NowEmptyBox";
import NowTodoSection from "./nowSection/NowTodoSection";

export default function Page() {
    return <main className="container-lg">
        <div className="container text-center">
            <div className="row">
                <NowTodoSection title={<h2>오늘 할일</h2>} uri="/" className="col" emptyDoc={<NowEmptyBox text="오늘은 할일이 없네요."/>} />
                <NowTodoSection title={<h2>지난 일</h2>} uri="/" className="col" />
            </div>
        </div>

        <NowTodoSection title={<h2>할일 리스트</h2>} uri="/" />
    </main>;
}