import History_en_US_Base from './history_en_US_base';

function getLocaleResource(){
    const History_en_US_OwnData = {};
    const targetData = Object.assign(History_en_US_Base(), History_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;