import Dept_zh_CN_Base from './dept_zh_CN_base';

function getLocaleResource(){
    const Dept_zh_CN_OwnData = {};
    const targetData = Object.assign(Dept_zh_CN_Base(), Dept_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;