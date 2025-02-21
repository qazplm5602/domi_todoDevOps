import ViewHeader from "../../../components/ViewPage/Head";
import ViewDescription from "../../../components/ViewPage/Desc";
import { ApiError, request } from "../../../hooks/request";
import { TodoData } from "../../../components/TodoForm/declare";
import { notFound, redirect } from "next/navigation";

type Props = {
    params: Promise<{ id: string }>
}

export async function generateMetadata({ params }: { params: Promise<{ id: string }> }) {
    const { id } = await params;
    const { code, data } = await request<TodoData>(`/todo/${id}`);

    let title = '';
    if (code === 200)
        title = data.title + ' ';
    
    return {
        title: title
    }
}

export default async function Page({ params }: Props) {
    const { id } = await params;
    const { code, data } = await request(`/todo/${id}`);
    if (code !== 200) {
        const response = data as ApiError;
        if (response.code === "USER1") // 로그인 필요
            redirect("/login");

        if (code === 404)
            notFound();

        throw new Error("불러오기 오류");
    }

    const response = data as TodoData;

    return <main className="container-lg">
        <ViewHeader id={response.id} title={response.title} date={response.startDate} />
        <ViewDescription value={response.description} />
    </main>
}