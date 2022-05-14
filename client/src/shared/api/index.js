export default class ServiceKotkovets {
    static _baseUrl = 'http://localhost:8080'

    static getResources = async (url) => {
        const res = await fetch(url)

        return await res.json()
    }

    static postResource = async (url, method, body) => {
        const res = await fetch(url, {
            method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        })

        return await res.json()
    }

    static register = (body) => {
        return this.postResource(`${this._baseUrl}/users/add`, 'POST', body)
    }

    static login = (body) => {
        return this.postResource(`${this._baseUrl}/users/login`, 'POST', body)
    }

    static recover = (body) => {
        return this.postResource(`${this._baseUrl}/users/recover`, 'PUT', body)
    }
    
    static home = (body) =>{
        return this.postResource(`${this._baseUrl}/users/home`, 'POST', body)
    }
    static savePersonalInf = (body, userId) =>{
        return this.postResource(`${this._baseUrl}/personalInf/add/${userId}`, 'POST', body)
    }

    static getUsersPersonalInfo = () => {
        return this.getResources(`${this._baseUrl}/personalInf/getAll`)
    }

    static editUserInfo = (body, id) => {
        return this.postResource(`${this._baseUrl}/personalInf/edit/${id}`, 'PUT', body)
    }

    static deleteUser = (userId) => {
        return this.postResource(`${this._baseUrl}/users/delete/${userId}`, 'DELETE')
    }

    static deletePersonalInf = (id) => {
        return this.postResource(`${this._baseUrl}/personalInf/delete/${id}`, 'DELETE')
    }

    static roleHandler = (id) => {
        return this.postResource(`${this._baseUrl}/users/handler/role/${id}`, 'PUT')
    }

    static compareCode = (body) => {
        return this.postResource(`${this._baseUrl}/comparing/compare`, 'POST', body)
    }

    static addComparison = (body, userId) => {
        return this.postResource(`${this._baseUrl}/comparisonResult/add/${userId}`, 'POST', body)
    }

    static getCompares = (userId) => {
        return this.getResources(`${this._baseUrl}/comparisonResult/getAll/${userId}`)
    }

    static getAllCompares = () => {
        return this.getResources(`${this._baseUrl}/comparisonResult/getAll`)
    }

    static getAllUsers = () => {
        return this.getResources(`${this._baseUrl}/users/getAll`)
    }

    static addDataComparison = (body, comparisonId) => {
        return this.postResource(`${this._baseUrl}/dataСomparison/add/${comparisonId}`, 'POST', body)
    }

    static fillLogs = (body, userId) => {
        return this.postResource(`${this._baseUrl}/visitStatistics/add/${userId}`, 'POST', body)
    }
    
    static getLogs = (activePage) => {
        return this.getResources(`${this._baseUrl}/visitStatistics/getAll?offset=${activePage - 1}&limit=9`)
    }

    static countLogs = () => {
        return this.getResources(`${this._baseUrl}/visitStatistics/count`)
    }

    static generalReport = () => {
        return this.getResources(`${this._baseUrl}/viewHistory/all`)
    }

    static customReportByUserId = (userId) => {
        return this.getResources(`${this._baseUrl}/viewHistory/${userId}`)
    }
}