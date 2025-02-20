import TodoFormDateInput from './DateInput';
import TodoFormDescInput from './DescInput';
import style from './style/style.module.scss';
import TodoFormTitleInput from './TitleInput';

export default function TodoForm() {
    return <main className={`container-lg ${style.main}`}>
        <form>
            <TodoFormTitleInput />
            <TodoFormDateInput />
            <TodoFormDescInput />

            <div className='btn-toolbar justify-content-end'>
                <button className='btn btn-primary' type='submit'>추가</button>
            </div>
        </form>
    </main>
}