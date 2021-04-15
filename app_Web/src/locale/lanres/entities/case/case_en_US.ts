import Case_en_US_Base from './case_en_US_base';

function getLocaleResource(){
    const Case_en_US_OwnData = {};
    const targetData = Object.assign(Case_en_US_Base(), Case_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;