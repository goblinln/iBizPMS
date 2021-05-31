import IbizproProjectWeekly_zh_CN_Base from './ibizpro-project-weekly_zh_CN_base';

function getLocaleResource(){
    const IbizproProjectWeekly_zh_CN_OwnData = {};
    const targetData = Object.assign(IbizproProjectWeekly_zh_CN_Base(), IbizproProjectWeekly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;