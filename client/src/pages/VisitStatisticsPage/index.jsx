import { Container } from "@mui/material"
import { Box } from "@mui/system"
import { useEffect, useState } from "react"
import { NavLink } from "react-router-dom"
import ServiceKotkovets from "../../shared/api"
import PaginationRounded from "../../shared/ui/Pagination"



const VisitStatisticsPage = () =>{
    const [items, setItems] = useState([]);
    const [totalCount, setTotalCount] = useState(0);

    useEffect(() => {
        ServiceKotkovets.getLogs(1)
            .then((data) => setItems(data))
            .catch(e => console.log('error'))
        ServiceKotkovets.countLogs()
            .then(data => setTotalCount(data))
            .catch(e => console.log(e))
    }, [])

    const content = items.length !== 0 && <View setItems={setItems} totalCount={totalCount} items={items} />

    return(
        <Container>
            <Box>
                <h1>---Статистика посещений---</h1>
                {content}
                <NavLink className="buttonUser__back" to="/home">Назад</NavLink>
            </Box>
        </Container>
    )
}

const View = ({items, totalCount, setItems}) => {
    const [activePage, setActivePage] = useState(1);

    useEffect(() => {
        ServiceKotkovets.getLogs(activePage)
            .then(data => setItems(data))
            .catch(e => console.log(e))
    }, [activePage])

    const content = items.map(({id, login, timeVisit, user}) => {
        const date = new Date(timeVisit);
        return (
            <li className="p_wrapperResult" key={id}>
                <div className="p_itemsResult">
                    <p>Логин: {login}</p>
                    <p>Роль: {user.role}</p>
                    <p>timeVisit: {date.getDate() + "." + Number(date.getMonth() + 1) + "." + date.getFullYear()}</p>
                </div>
            </li>
        )
    })
    return (
        <>
            <div className="p_section">
                {content}
            </div>
            <PaginationRounded setActivePage={setActivePage} size={Math.ceil(totalCount / 9)}/>
        </>
    )
}

export default VisitStatisticsPage
