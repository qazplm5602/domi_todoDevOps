import { getCurrentUser } from '../../hooks/loginUser';
import HeaderGuestButtons from './Guest/GuestButton';
import style from './header.module.scss';

export default async function Header() {
    const user = await getCurrentUser();

    return <header className={`navbar navbar-expand-lg ${style.main} sticky-top`}>
        <h1 className='h4 text-white'>TodoList</h1>

        <HeaderGuestButtons />
    </header>;
}