# 月报（待阅）

月报移动端编辑视图(IbzMonthlyMobEditView)

{% plantuml %}

digraph IbzMonthlyMobEditView {

label="月报（待阅）";    

#rankdir=LR;

start [shape=circle,size="1,1",label="",style=filled, fillcolor=black]
end [shape=doublecircle,label=""]

own [shape=none, margin=0,size="6,6", label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">月报（待阅）<BR/><BR/></TD>
</TR>
</TABLE>
>];

part [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">P</TD> 
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">


<BR/></TD>
</TR>
</TABLE>
>];


MobEdit [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">修改<BR/><BR/></TD>
</TR>
</TABLE>
>];



MobSubmit [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">L</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">提交<BR/><BR/></TD>
</TR>
</TABLE>
>];



start -> own ;
own -> part [style=dashed,arrowhead=diamond,label="<包含>"] ;


part -> MobEdit [style=dashed,label=<修改<BR/>(迁移/popup)>];

MobEdit -> end ;



part -> MobSubmit [style=dashed,label=<提交>];

MobSubmit -> end ;



}

{% endplantuml %}

