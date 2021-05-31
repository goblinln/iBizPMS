import CaseStats_zh_CN_Base from './case-stats_zh_CN_base';

function getLocaleResource(){
    const CaseStats_zh_CN_OwnData = {};
    const targetData = Object.assign(CaseStats_zh_CN_Base(), CaseStats_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;