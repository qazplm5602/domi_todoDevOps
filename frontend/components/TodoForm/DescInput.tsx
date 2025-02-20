export default function TodoFormDescInput({ initVal }: { initVal?: string }) {
    return <div className="mb-3">
        <label htmlFor="description" className="form-label">설명</label>
        <textarea className="form-control" aria-label="With textarea" name="desc" style={{ minHeight: 300 }} defaultValue={initVal}></textarea>
        <div className="form-text" id="basic-addon4">이 스케줄에 대해 메모하세요.</div>
    </div>;
}