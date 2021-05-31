import EmpLoyeeload_en_US_Base from './emp-loyeeload_en_US_base';

function getLocaleResource(){
    const EmpLoyeeload_en_US_OwnData = {};
    const targetData = Object.assign(EmpLoyeeload_en_US_Base(), EmpLoyeeload_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;