import { Box, Button, Container, Paper, TextField } from '@mui/material';
import React, { useState } from 'react';
import ServiceKotkovets from '../../shared/api';

const RecoverPage = () => {
    const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto"}
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [secretCode, setSecretCode] = useState('');
    const handleClick = (e)=>{
		e.preventDefault()
		ServiceKotkovets.recover({login, password, secretCode})
			.then((state) => state ? alert('Пароль изменён') : Promise.reject())
			.catch(() => alert('Неверный логин или секретный код'))
    }
    return (
        <Container>
			<Paper elevation = {3} style = {paperStyle}>
				<h1>Восстановление пароля</h1>
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
						id="outlined-password-input"
						label="Логин"
						autoComplete="current-password"
						fullWidth
						value={login}
						name="password"
						onChange={(e)=>setLogin(e.target.value)}
					/>
					<TextField
						required
						id="outlined-password-input"
						label="Новый пароль"
						type="password"
						autoComplete="current-password"
						fullWidth
						value={password}
						name="password"
						onChange={(e)=>setPassword(e.target.value)}
					/>
                    <TextField
						required
						id="outlined-password-input"
						label="Секретный код"
						autoComplete="current-password"
						fullWidth
						value={secretCode}
						name="password"
						onChange={(e)=>setSecretCode(e.target.value)}
					/>
					<Button type='submit' className="button_reg" variant='contained'>Изменить пароль</Button>
				</Box>
			</Paper>
		</Container>
    );
};

export default RecoverPage;