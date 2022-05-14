import React, { useState } from 'react';
import { BrowserRouter } from 'react-router-dom';
import { CurrentUserContext, RoleContext } from '../context';

const Providers = (props) => {
    const [role, setRole] = useState('')
    const [currentUser, setCurrentUser] = useState(null)

    return (
        <BrowserRouter>
			<RoleContext.Provider value={{role, setRole}}>
                <CurrentUserContext.Provider value={{currentUser, setCurrentUser}}>
                    {props.children}
                </CurrentUserContext.Provider>
            </RoleContext.Provider>
        </BrowserRouter>
    );
};

export default Providers;