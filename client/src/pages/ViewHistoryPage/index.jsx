import { Container } from "@mui/material"
import { Box } from "@mui/system"
import { useEffect, useState } from "react"
import { NavLink } from "react-router-dom"
import ServiceKotkovets from "../../shared/api"


import './ViewHistoryPage.css'

const ViewHistoryPage = () => {
    const [items, setItems] = useState([])
    const [users, setUsers] = useState([])
    const createReportAll = () => {
        ServiceKotkovets.generalReport()
        alert("Отчёт создан.")
    }
    useEffect(() => {
        ServiceKotkovets.getAllCompares()
            .then(data => setItems(data))
            .catch(e => console.log(e))
        ServiceKotkovets.getAllUsers()
            .then(data => setUsers(data))
            .catch(e => console.log(e))
    }, [])
    
    const onSort = () => {
        const itemsToSort = [...items];
        setItems(itemsToSort.sort((a, b) => a.percentComparison - b.percentComparison))
    }

    const onDisableSort = () => {
        ServiceKotkovets.getAllCompares()
            .then(data => setItems(data))
            .catch(e => console.log(e))
    }

    const onSelectByUser = (e) => {
        if (e.target.value === 'all') {
            ServiceKotkovets.getAllCompares()
                .then(data => setItems(data))
                .catch(e => console.log(e))
        } else {
            ServiceKotkovets.getCompares(e.target.value)
            .then(data => setItems(data))
            .catch(e => console.log(e))
        }
    }

    const content = items.length !== 0 && <View items={items} />

    return(
        <Container>
            <Box>
                <h1>---Просмотр историй сравнений пользователей---</h1>
                <button className="history__writeHistory" onClick={createReportAll}>Записать отчёт</button>
                <button onClick={onSort} className="history__writeHistory">Отсортировать по проценту уникальности</button>
                <button onClick={onDisableSort} className="history__writeHistory">Отменить сортировку</button>
                <select className="select__users" onChange={onSelectByUser}>
                    <option value="all">Все</option>
                    {users.filter(item => item.login !== 'admin').map(item => {
                        return <option key={item.login} value={item.id}>{item.login}</option>
                    })}
                </select>
                <NavLink className="buttonUser__back" to="/home">Назад</NavLink>
                {content}
            </Box>
        </Container>
    )
}

const View = ({ items }) => {
    const content = items.map(({id, titleСomparison, timeComparison, percentComparison, user}, i) => {
        const date = new Date(timeComparison)
        return (
            <li className="p_wrapperResult" key={id}>
                <div className="p_itemsResult">
                    <p>Проверка №: {i + 1}</p>
                    <p>Название: {titleСomparison}</p>
                    <p>Процент совпадения: {Number(percentComparison).toFixed(2)}</p>
                    <p>Время проверки: {date.getDate() + "." + Number(date.getMonth() + 1) + "." + date.getFullYear()}</p>
                    <p>Пользователь: {user.login}</p>
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

export default ViewHistoryPage