export default function TodoFormTitleInput() {
    return <div className="mb-3">
        <label className="form-label">제목</label>
        <div className="input-group">
            <input type="text" className="form-control" name="title" aria-describedby="basic-addon3 basic-addon4" />
        </div>
    </div>;
}