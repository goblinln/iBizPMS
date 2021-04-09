import IbzDaily_zh_CN_Base from './ibz-daily_zh_CN_base';

function getLocaleResource(){
    const IbzDaily_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzDaily_zh_CN_Base(), IbzDaily_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;