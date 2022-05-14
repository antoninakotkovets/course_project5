import React, { useContext, useEffect } from 'react';
import {Route, Routes, useNavigate} from 'react-router-dom'

import LoginPage from './LoginPage';
import RegistrationPage from './RegistrationPage';
import HomePage from './HomePage';
import { RoleContext } from '../app/context';
import PersonalInfoPage from './PersonalInfoPage';
import UsersPage from './UsersPage';
import ViewHistoryPage from './ViewHistoryPage';
import ResultPage from './ResultPage';
import VisitStatisticsPage from './VisitStatisticsPage'
import FirstPage from './FirstPage';
import RecoverPage from './RecoverPage';

const Pages = () => {
    const navigate = useNavigate()
    const {role} = useContext(RoleContext)

    useEffect(() => {
        navigate("/firstPage")
    }, [])

    return (
        <Routes>
            <Route path="/firstPage" element = {<FirstPage/>}/>
            <Route path="/login" element={<LoginPage />}/>
            <Route path="/registration" element={<RegistrationPage />}/>
            <Route path="/recover" element={<RecoverPage />} />
            { role &&
                <>

                    <Route path="/home" element={<HomePage />}/>
                    <Route path="/exit" element={<LoginPage />}/>
                    <Route path="/personalInfo" element = {<PersonalInfoPage />}/>
                    <Route path="/users" element = {<UsersPage/>}/>
                    <Route path ="/viewHistory" element = {<ViewHistoryPage/>}/>
                    <Route path="/myComparison" element = {<ResultPage/>}/>
                    <Route path="/visitStatistics" element = {<VisitStatisticsPage/>}/>
                </>
            }
        </Routes>
    );
};

export default Pages;