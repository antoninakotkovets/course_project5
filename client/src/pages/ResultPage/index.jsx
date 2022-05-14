import { Container } from "@mui/material"
import { Box } from "@mui/system"
import { useContext, useEffect, useState } from "react"
import { NavLink } from "react-router-dom"
import { CurrentUserContext } from "../../app/context"
import ServiceKotkovets from "../../shared/api"

import './ResultPage.css'

const ResultPage = () => {
    const [items, setItems] = useState([]);
    const {currentUser} = useContext(CurrentUserContext)
    useEffect(() => {
        ServiceKotkovets.getCompares(currentUser)
            .then((data) => setItems(data))
            .catch(() => console.log("error")) 
    }, [])

    const content = items.length !== 0 && <View items={items}/>
    const createCustomReport = () => {
        ServiceKotkovets.customReportByUserId(currentUser);
        alert("Отчёт создан.")
    }

    return(
        <Container>
            <Box>
                <h1>---Мои сравнения---</h1>
                <NavLink className="buttonUser__back" to="/home">Назад</NavLink>
                <button className="button__file" onClick={createCustomReport}>Записать в отчет</button>
                {content}
            </Box>
        </Container>
    )
}

const View = ({ items }) => {
    const content = items.map(({id, titleСomparison, timeComparison, percentComparison}) => {
        const date = new Date(timeComparison)
        return (
            <li className="p_wrapperResult" key={id}>
                <div className="p_itemsResult">
                    <p></p>
                    <p>Название: {titleСomparison}</p>
                    <p>Процент совпадения: {Number(percentComparison).toFixed(2)}</p>
                    <p>Время проверки: {date.getDate() + "." + Number(date.getMonth() + 1) + "." + date.getFullYear()}</p>
                </div>
            </li>
        )
    })
    return (
        <div className="p_section">
            {content}
        </div>
    )
}

export default ResultPage