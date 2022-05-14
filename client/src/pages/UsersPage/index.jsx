import { Container, TextField } from "@mui/material"
import { Box } from "@mui/system"
import { useEffect, useState } from "react"
import { NavLink } from "react-router-dom"
import ServiceKotkovets from "../../shared/api"
import Modal from "../PersonalInfoPage"

import './usersPage.css'

const UsersPage = () =>{
    const [personalInfo, setPersonalInfo] = useState([])
    const[modal, setModal] = useState(false)
    const [surname, setSurname] = useState('');
	const [name, setName] = useState('');
	const [lastName, setlastName] = useState('');
	const [gender, setGender] = useState('Мужской');
	const [date, setDate] = useState('');
    
    const onGetInfo = () => {
        ServiceKotkovets.getUsersPersonalInfo()
            .then(data => setPersonalInfo(data))
            .catch(() => console.log('error'))
    }

    useEffect(() => {
        onGetInfo()
    }, [])

    const editData = (e) => {
        e.preventDefault()
		ServiceKotkovets.editUserInfo({surname, name, lastName, gender}, modal)
			.then((data) => {
                const idx = personalInfo.findIndex(item => item.id === modal)
                const newItem = data
                setPersonalInfo([...personalInfo.slice(0, idx), data, ...personalInfo.slice(idx + 1)])
            })
			.catch(()=> alert('Не корректно введены данные!'))
    }

    const content = personalInfo.length !== 0 && <View setPersonalInfo={setPersonalInfo} onGetInfo={onGetInfo} setModal={setModal} personalInfo={personalInfo}/>

    return(
        <Container>
            <Box>
                <h1>Управление пользователями</h1>
                <NavLink className="buttonUser__back" to="/home">Назад</NavLink>
                {content}
            </Box>
            <Modal
                title={'Редактирование'}
                isOpened={modal}
                onModalClose = {() => setModal(false)}
            >
                <form onSubmit={editData}>
                    <TextField className='modal__surname' 
                        required
                        id="outlined-required"
                        label="Фамилия"
                        value={surname}
                        onChange={(e)=>setSurname(e.target.value)}
                        fullWidth> 
                    </TextField>
                    <TextField className='modal__name'
                        required
                        id="outlined-required"
                        label="Имя"
                        value={name}
                        onChange={(e)=>setName(e.target.value)}
                        fullWidth> 
                    </TextField>
                    <TextField className='modal__lastname'
                        required
                        id="outlined-required"
                        label="Отчество"
                        value={lastName}
                        onChange={(e)=>setlastName(e.target.value)}
                        fullWidth> 
                    </TextField>
                    <p className="modal__gender">Укажите Ваш пол:</p>
                    <select required value={gender} className="home__select"
                    onChange={(e)=>setGender(e.target.value)}>
                        <option>Мужской</option>
                        <option>Женский</option>
                    </select>
                    <p className="modal__textDate">Дата рождения:</p>
                    <input required className="modal__date" type="date" max="2015-01-01" min="1990-01-01" 
                    value={date} 
                    onChange={(e)=>setDate(e.target.value)}></input>
                    <div>
                        <button type='submit' className="modal__dataSave">Сохранить</button>
                    </div>
                </form>
            </Modal>
        </Container>
    )
}

const View = ({ personalInfo, setModal, onGetInfo, setPersonalInfo }) => {
    const onDelete = (id, userId) => {
        ServiceKotkovets.deleteUser(id)
            .then(() => alert('Удалено'))
            .catch(e => console.log(e))
        ServiceKotkovets.deletePersonalInf(userId)
            .then(() => onGetInfo())
            .catch(e => console.log(e))
    }

    const onChangeRole = async (id) => {
        await ServiceKotkovets.roleHandler(id)
        ServiceKotkovets.getUsersPersonalInfo()
            .then(data => setPersonalInfo(data))
            .catch(() => console.log('error'))
    }

    const content = personalInfo.map(({ id, surname, name, lastName, gender, date: birthDate, user}) => {
        const date = new Date(birthDate)
        console.log(user);
        return (
            <li className="p_wrapper" key={id}>
                <div className="p_items">
                    <p>Логин: {user.login}</p>
                    <p>Роль: {user.role}</p>
                    <p>Фамилия: {surname ? surname: 'не задано'}</p>
                    <p>Имя: {name ? name : 'не задано'}</p>
                    <p>Отчество: {lastName ? lastName: 'не задано'}</p>
                    <p>Пол: {gender}</p>
                    <p>Дата рождения: {date.getDate() + "." + date.getMonth() + "." + date.getFullYear()}</p>
                </div>
                <div className="p_btns">
                
                    <button className="users__delete" onClick={() => onDelete(user.id, id)}>Удалить</button>
                    <button className="users__edit" onClick={() => setModal(id)}>Редактировать</button>
                    <button className="users__roleEdit" onClick={() => onChangeRole(user.id)}>Изменить права доступа</button>
                </div>
            </li>
            
        )
    })
    
    return (
        <section className="p_section">
            {content}
        </section>
    )
}

export default UsersPage