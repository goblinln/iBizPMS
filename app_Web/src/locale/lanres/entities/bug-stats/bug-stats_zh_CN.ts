import BugStats_zh_CN_Base from './bug-stats_zh_CN_base';

function getLocaleResource(){
    const BugStats_zh_CN_OwnData = {};
    const targetData = Object.assign(BugStats_zh_CN_Base(), BugStats_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;