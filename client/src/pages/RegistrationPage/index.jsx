import { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button, Container, Paper} from '@mui/material';
import { nanoid } from 'nanoid';

import ServiceKotkovets from '../../shared/api';

import './registrationPage.css'

const RegistrationPage = () => {
    const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto"}
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const handleClick = (e)=>{
		e.preventDefault()
		const secretCode = nanoid();
		ServiceKotkovets.register({login, password, secretCode})
			.then((state) => state ? alert('Вы зарегистрировались, ваш секретный код: ' + secretCode + '. Запомните его, ведь с помощью него можно восстановить пароль.') : Promise.reject())
			.catch(() => alert('Пользователь с таким логином уже существует'))
    }
    return (
		<Container>
			<Paper elevation = {3} style = {paperStyle}>
				<h1>Регистрация</h1>
				<Box
					onSubmit={handleClick}
					component="form"
					sx={{
						'& .MuiTextField-root': { m: 1, width: '50ch' },
					}}
					noValidate
					autoComplete="off"
				>
					<TextField
						required
						id="outlined-required"
						label="Логин"
						fullWidth
						value={login}
						name="login"
						onChange={(e)=>setLogin(e.target.value)}
					/>
					<TextField
						required
						id="outlined-password-input"
						label="Пароль"
						type="password"
						autoComplete="current-password"
						fullWidth
						value={password}
						name="password"
						onChange={(e)=>setPassword(e.target.value)}
					/>
					<Button type='submit' className="button_reg" variant='contained'>Зарегистрироваться</Button>
				</Box>
			</Paper>
		</Container>
  	);
}

export default RegistrationPage