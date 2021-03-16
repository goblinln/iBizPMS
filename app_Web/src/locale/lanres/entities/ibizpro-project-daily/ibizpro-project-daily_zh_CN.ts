import IbizproProjectDaily_zh_CN_Base from './ibizpro-project-daily_zh_CN_base';

function getLocaleResource(){
    const IbizproProjectDaily_zh_CN_OwnData = {};
    const targetData = Object.assign(IbizproProjectDaily_zh_CN_Base(), IbizproProjectDaily_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;