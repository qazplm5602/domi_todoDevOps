import { Params } from "next/dist/server/request/params"
import ViewHeader from "../../../components/ViewPage/Head";
import ViewDescription from "../../../components/ViewPage/Desc";

type Props = {
    params: Params
}

export default async function Page({ params }: Props) {
    const { id } = await params;
    return <main className="container-lg">
        <ViewHeader />
        <ViewDescription />
    </main>
}