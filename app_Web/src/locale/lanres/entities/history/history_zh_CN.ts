import History_zh_CN_Base from './history_zh_CN_base';

function getLocaleResource(){
    const History_zh_CN_OwnData = {};
    const targetData = Object.assign(History_zh_CN_Base(), History_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;