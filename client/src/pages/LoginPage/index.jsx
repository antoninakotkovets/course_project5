import { useContext, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Link, useNavigate } from 'react-router-dom';
import { Button, Container, Paper} from '@mui/material';

import ServiceKotkovets from '../../shared/api';
import { CurrentUserContext, RoleContext } from '../../app/context';

import './loginPage.css'

function LoginPage() {
    const paperStyle = { padding: '50px 20px', width: 600, margin: "20px auto" };
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const {role, setRole} = useContext(RoleContext);
    const {currentUser, setCurrentUser} = useContext(CurrentUserContext)
    const navigate = useNavigate()

    const handleClick = (e) => {
        ServiceKotkovets.login({ login, password })
            .then((data) => data ? saveRole(data) : Promise.reject())
            .catch(() => alert('Неверный логин или пароль'));
    };

    const saveRole = (data) =>{
        setRole(data.role)
        setCurrentUser(data.id)
        ServiceKotkovets.fillLogs({login: data.login, timeVisit: new Date()}, data.id)
        navigate("/home")
    }

    return (
        <Box className="container">
            <Container>
                <Paper elevation={3} style={paperStyle}>
                    <h1>Авторизация</h1>
                    <Box
                        component="form"
                        sx={{
                            '& .MuiTextField-root': { m: 1, width: '50ch' },
                        }}
                        noValidate
                        autoComplete="off"
                    >
                        <div className='login__wrapper'>
                            <TextField
                                required
                                id="outlined-required"
                                label="Логин"
                                fullWidth
                                value={login}
                                onChange={(e) => setLogin(e.target.value)} />
                            <TextField className="textPassword"
                                id="outlined-password-input"
                                label="Пароль"
                                type="password"
                                autoComplete="current-password"
                                fullWidth
                                value={password}
                                onChange={(e) => setPassword(e.target.value)} />
                            <Button className="button_enter" onClick={handleClick} >Войти</Button>
                            <Link className="recoverLink" to="/recover">Забыли пароль?</Link>
                        </div>
                    </Box>
                </Paper>
            </Container>
        </Box>
    );
}

export default LoginPage;