import IbzAgent_zh_CN_Base from './ibz-agent_zh_CN_base';

function getLocaleResource(){
    const IbzAgent_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzAgent_zh_CN_Base(), IbzAgent_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;