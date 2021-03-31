$(document).ready(function() {
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        eventClick: function(calEvent, jsEvent, view) {

            alert('Event: ' + calEvent.title+'\n'+ 'Description: '+calEvent.description);
            // change the border color just for fun
            $(this).css('border-color', 'red');

        },
        defaultDate: moment().format("YYYY-MM-DD"),
        nowIndicator: true,
        navLinks: true,
        selectable: true,
        handleWindowResize: true,
        fixedWeekCount:false,
        aspectRatio:1,
        eventLimit: true, // allow "more" link when too many events
        eventDidMount: function(info) {
            var tooltip = new Tooltip(info.el, {
                title: info.event.extendedProps.description,
                placement: 'top',
                trigger: 'hover',
                container: 'body'
            });
        },
        selectable: true,
        selectHelper: true,
        editable: true,
        eventLimit: true,
        events: {
            url: '/allevents',
            type: 'GET',
            error: function() {
                alert('there was an error while fetching events!');
            },
        },
        eventColor: '#ffffff',
        eventBackgroundColor:'#009961',
    });

});