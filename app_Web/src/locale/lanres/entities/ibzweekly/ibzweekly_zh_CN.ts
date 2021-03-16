import IBZWEEKLY_zh_CN_Base from './ibzweekly_zh_CN_base';

function getLocaleResource(){
    const IBZWEEKLY_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZWEEKLY_zh_CN_Base(), IBZWEEKLY_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;