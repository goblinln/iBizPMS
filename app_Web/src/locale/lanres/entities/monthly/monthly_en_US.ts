import Monthly_en_US_Base from './monthly_en_US_base';

function getLocaleResource(){
    const Monthly_en_US_OwnData = {};
    const targetData = Object.assign(Monthly_en_US_Base(), Monthly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;