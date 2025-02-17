type Props = {
    icon?: React.ReactNode,
    type?: React.HTMLInputTypeAttribute,
    placeholder?: string,
    name?: string
}

export default function LoginInput({ icon, type, placeholder, name }: Props) {
    return <div className="input-group mb-3">
    {icon && <span className="input-group-text" id="basic-addon1">{icon}</span>}
    <input type={type} className="form-control" placeholder={placeholder} aria-label={placeholder} name={name} aria-describedby="basic-addon1" />
  </div>
}