import Reportly_zh_CN_Base from './reportly_zh_CN_base';

function getLocaleResource(){
    const Reportly_zh_CN_OwnData = {};
    const targetData = Object.assign(Reportly_zh_CN_Base(), Reportly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;