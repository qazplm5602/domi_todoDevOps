export default function TodoFormDateInput({ initVal }: { initVal?: string }) {
    return <div className="input-group mb-3">
        <span className="input-group-text" id="basic-addon1">📅</span>
        <input type="datetime-local" className="form-control" name="date" placeholder="시간" aria-label="Username" aria-describedby="basic-addon1" defaultValue={initVal} />
    </div>;
}