var size=[[${reviews.size()}]];
var size1=[[${sizeOf1}]];
var size2=[[${sizeOf2}]];
var size3=[[${sizeOf3}]];
var size4=[[${sizeOf4}]];
var size5=[[${sizeOf5}]];
var dataset = [
    {
        name: "Grade 1",
        percent: size1
    },{
        name: "Grade 2",
        percent: size2
    },{
        name: "Grade 3",
        percent: size3
    },{
        name: "Grade 4",
        percent: size4
    },{
        name: "Grade 5",
        percent: size5
    }
];
var w=300,h=300;
var radius=(w-20)/2;
var pie=d3.layout.pie()
    .value(function(d){return d.percent})
    .sort(null);
var arc=d3.svg.arc()
    .innerRadius(0)
    .outerRadius(radius);
var color = d3.scale.ordinal()
    .range([ '#e75244','#009961']);
var svg=d3.select("#chart")
    .append("svg")
    .attr({
        width:w,
        height:h,
    }).append('g')
    .attr('transform','translate('+(w/2)+','+(h/2)+')');
var path=svg.selectAll('path')
    .data(pie(dataset))
    .enter()
    .append('path')
    .attr({
        d:arc,
        fill:function(d,i){
            return color(i);
        }
    })
    .style({
        'fill-opacity':.15,
        stroke: function(d,i){
            return color(i);
        },
        'stroke-width': '2px'
    });
var text=svg.selectAll('text')
    .data(pie(dataset))
    .enter()
    .append("text")
    .attr("transform", function (d) {
        return "translate(" + arc.centroid(d) + ")";
    })
    .attr("text-anchor", "middle")
    .text(function(d){
        return d.data.name+" ("+Math.round(((d.data.percent)/size)*100)+"%)" ;
    })
    .style({
        fill:function(d,i){
            return color(i);
        },
        'font-size':'10px',
    });