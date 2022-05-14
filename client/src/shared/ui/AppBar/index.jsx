import { useContext } from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { NavLink } from 'react-router-dom'

import './AppBar.css'
import { RoleContext } from '../../../app/context';

export default function Appbar() {
  const { role, setRole } = useContext(RoleContext)
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
			<Typography variant="h6" component="div" sx={{ flexGrow: 1 }}></Typography>
			{role === 'user' && 
				<div>
					
				</div>

			}
			{role === 'admin' && 
				<div>
					
				</div>
		
			}
			{role === '' ?
				<div>
					<NavLink className={({ isActive }) => (isActive ? "bar__link_active" : "bar__link")} to="/login">Авторизация</NavLink>
					<NavLink className={({ isActive }) => (isActive ? "bar__link_active" : "bar__link")} to="/registration">Регистрация</NavLink>
				</div>
			:
				<button>
					<NavLink className="button__exit" to="/login" onClick={() => setRole('')}>Выход</NavLink>
				</button>
				
			}
        </Toolbar>
      </AppBar>
    </Box>
  );
}