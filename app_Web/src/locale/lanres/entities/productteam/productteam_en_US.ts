import PRODUCTTEAM_en_US_Base from './productteam_en_US_base';

function getLocaleResource(){
    const PRODUCTTEAM_en_US_OwnData = {};
    const targetData = Object.assign(PRODUCTTEAM_en_US_Base(), PRODUCTTEAM_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;