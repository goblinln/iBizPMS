import { ThirdPartyService } from "../..";

/**
 * 动画服务类
 *
 * @class AnimationService
 */
export class AnimationService {

    /**
     * 长按元素拖动
     */
    public static draggable(ele:HTMLElement|null,event:Function,timer:number=1000) {
        if(!ele){
            return 
        }
        let l:number = 0;
        let x :number= 0;
        let y :number= 0;
        let right :number= 0;
        let bottom :number= 0;
        let time = 0
        let canMove:boolean = false
        // 开始长按
        ele.addEventListener("touchstart",(e:any)=>{
            l =  e.srcElement.offsetLeft
            x = e.targetTouches[0].clientX;
            y = e.targetTouches[0].clientY;
            if(ele){
                 right = Number(ele.style.right .replace('px',""));
                 bottom = Number(ele.style.bottom.replace('px',""));
            }
            time = setTimeout(() => {
                if(ThirdPartyService.getInstance().platform){
                    ThirdPartyService.getInstance().thirdPartyEvent('vibrate',100);
                }
                canMove = true;
            }, timer);
        })

        // 开始移动
        ele.addEventListener("touchmove",(e:any)=>{
            clearTimeout(time);
            if(!canMove){
                return
            }
            let _x = e.targetTouches[0].clientX;
            let _y = e.targetTouches[0].clientY;
            let move_y = (_y - y);
            let move_x = (_x - x );
            event({right:right - move_x+'px',bottom:bottom - move_y+'px'});
        })
        // 开始移动
        ele.addEventListener("touchend",(e:any)=>{
            clearTimeout(time);
            canMove = false;
        })
    }


}