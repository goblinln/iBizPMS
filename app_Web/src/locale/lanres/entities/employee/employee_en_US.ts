import Employee_en_US_Base from './employee_en_US_base';

function getLocaleResource(){
    const Employee_en_US_OwnData = {};
    const targetData = Object.assign(Employee_en_US_Base(), Employee_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;