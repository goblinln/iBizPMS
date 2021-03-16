import Dept_en_US_Base from './dept_en_US_base';

function getLocaleResource(){
    const Dept_en_US_OwnData = {};
    const targetData = Object.assign(Dept_en_US_Base(), Dept_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;