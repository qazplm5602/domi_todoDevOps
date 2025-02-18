import { Params } from "next/dist/server/request/params"

type Props = {
    params: Params
}

export default async function Page({ params }: Props) {
    const { id } = await params;
    return <main className="container-lg">
        <h3>id: {id}</h3>
    </main>
}