import Daily_en_US_Base from './daily_en_US_base';

function getLocaleResource(){
    const Daily_en_US_OwnData = {};
    const targetData = Object.assign(Daily_en_US_Base(), Daily_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;