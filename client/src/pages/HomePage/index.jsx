import Box from '@mui/material/Box';
import { Container } from '@mui/material';
import { TextField } from '@mui/material';
import { useContext, useState } from 'react';
import ServiceKotkovets from '../../shared/api';
import Modal from '../PersonalInfoPage'
import ReCAPTCHA from "react-google-recaptcha";

import './homePage.css'
import { CurrentUserContext, RoleContext } from '../../app/context';
import { NavLink } from 'react-router-dom';


const HomePage = () => {
	const [firstText, setFirstText] = useState('')
	const [secondText, setSecondText] = useState('')
	const [titleСomparison, setTitleComparison] = useState('')
	const [language, setLanguage] = useState('JAVA')
	const [modal, setModal] = useState(false)
	const [duplicates, setDuplicates] = useState([])
	const [surname, setSurname] = useState('');
	const [name, setName] = useState('');
	const [lastName, setlastName] = useState('');
	const [gender, setGender] = useState('Мужской');
	const [date, setDate] = useState('');
	const { role } = useContext(RoleContext)
	const { currentUser } = useContext(CurrentUserContext)
	const [compareResult, setCompareResult] = useState(null)
	const [newText, setNewText] = useState([])
	const [isLoaded, setIsLoaded] = useState(false)
	
	const onCompare = (e) => {
		e.preventDefault()
		ServiceKotkovets.compareCode({ firstText, secondText, language })
			.then(({ result, similarities }) => {
				alert('Сравнение прошло успешно, процент совпадения: ' + result.toFixed(2))
				setCompareResult(result)
				setDuplicates(similarities)
				setNewText(firstText.split(" ").map((item, i) => {
					console.log(item)
					console.log(item[item.length - 1])
					if (similarities.includes(item)) {
						return <span key={i} style={{color: 'red'}}>{item} {(item.includes('{') || item.includes('}') || item.includes(';')) && <br/>}</span>
					}
					
					return <span key={i}>{item} {(item.includes('{') || item.includes('}') || item.includes(';')) && <br/>}</span>;
				}))
				setIsLoaded(true)
				ServiceKotkovets.addComparison({percentComparison: result.toFixed(2), timeComparison: new Date(), titleСomparison}, currentUser)
					.then(data => { 
						ServiceKotkovets.addDataComparison({ lengthFirstText: firstText.length, lengthSecondText: secondText.length, language }, data)
					})
					.catch(e => console.log(e))
			})
			.catch(() => alert('Что-то пошло не так'))
	}

	const saveClick = (e) =>{
		e.preventDefault()
		ServiceKotkovets.savePersonalInf({modal, surname, name, lastName, gender, date}, currentUser)
			.then((state) => state ? alert ('Данные добавлены!'): Promise.reject())
			.catch(()=> alert('Не корректно введены данные!'))
	}
	function onChange(value) {
		alert("Проверка на робота уcпешно пройдена!");
		console.log("Captcha value:", value);
	}

    return (
		<Container>
				<Box>
					{role === 'user' &&
						<>
							<h1>---Проверка на схожесть программных кодов---</h1>
							<Modal
								title={'Проблемные участки'}
								isOpened = {isLoaded}
								onModalClose = {() => setIsLoaded(false)}
							>
								<div style={{textAlign: 'left'}}>{newText}</div>
							</Modal>
						</>	
							
					}

					{role === 'admin' &&
						<>
							<h1>---Панель администратора---</h1>
							<NavLink className="button__users" to="/users">Управление пользователями</NavLink>
							<NavLink className="buttom__viewHistory" to="/viewHistory">Просмотр историй сравнений</NavLink>
							<NavLink className="button__visitStatistics" to= "/visitStatistics">Статистика посещений</NavLink>
						</>
					}
				</Box>
				<div style={{display: modal === false ? 'none' : 'block'}} className="overlay"></div>
				<Modal
					title={'Персональные данные'}
					isOpened = {modal}
					onModalClose = {() => setModal(false)}
				>
					<form onSubmit={saveClick}>
					    <ReCAPTCHA sitekey="6Ld8w9MfAAAAAIIqJk0BQi7qCaYQp704e2LIzN-U" onChange={onChange}/>
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

				{role === 'user' &&
					<>
						<button className="buttonPersonalInfo" onClick={() => setModal(true)}>Персональные данные</button>
						<NavLink className="button__myComparison" to='/myComparison'>Мои сравнения</NavLink>
							<form onSubmit={onCompare}>
							<TextField className='titleComparison' required id="outlined-required" label="Название сравнения" onChange={(e) => setTitleComparison(e.target.value)}></TextField>
							<select onChange={e => setLanguage(e.target.value)} className='select__language'>
								<option value="JAVA">JAVA</option>
								<option value="PYTHON">PYTHON</option>
								<option value="C">C++</option>
							</select>
							<div className="div__textarea">
								<textarea className="home__textarea1" rows="30" cols ="60" required onChange={(e) => setFirstText(e.target.value)}></textarea>
								<textarea className="home__textarea2" rows="30" cols ="60" required onChange={(e) => setSecondText(e.target.value)}></textarea>		
							</div>
							<button className="button__compare">Сравнить</button>
							
						
						</form>
					</>
				}
				
				
		</Container>
  	);
}
export default HomePage