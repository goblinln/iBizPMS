import Employee_zh_CN_Base from './employee_zh_CN_base';

function getLocaleResource(){
    const Employee_zh_CN_OwnData = {};
    const targetData = Object.assign(Employee_zh_CN_Base(), Employee_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;