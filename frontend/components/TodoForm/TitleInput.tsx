export default function TodoFormTitleInput({ initVal }: { initVal?: string }) {
    return <div className="mb-3">
        <label className="form-label">제목</label>
        <div className="input-group">
            <input type="text" className="form-control" name="title" aria-describedby="basic-addon3 basic-addon4" defaultValue={initVal} />
        </div>
    </div>;
}