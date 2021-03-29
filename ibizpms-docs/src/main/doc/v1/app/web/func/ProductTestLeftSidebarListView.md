# 测试边栏

所有测试(ProductTestLeftSidebarListView)

{% plantuml %}

digraph ProductTestLeftSidebarListView {

label="测试边栏";    

#rankdir=LR;

start [shape=circle,size="1,1",label="",style=filled, fillcolor=black]
end [shape=doublecircle,label=""]

own [shape=none, margin=0,size="6,6", label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">测试边栏<BR/><BR/></TD>
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


TestManager [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">管理<BR/><BR/></TD>
</TR>
</TABLE>
>];



Refresh [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">刷新<BR/><BR/></TD>
</TR>
</TABLE>
>];



start -> own ;
own -> part [style=dashed,arrowhead=diamond,label="<包含>"] ;


part -> TestManager [style=dashed,label=<管理<BR/>(迁移/popup)>];

TestManager -> end ;



part -> Refresh [style=dashed,label=<刷新>];

Refresh -> end ;



}

{% endplantuml %}

