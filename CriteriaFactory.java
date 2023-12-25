public class CriteriaFactory {

    public FilterIF getcriteria(String type,String criteria)
    {
        if(type.equals("Subject"))
        {
            SubjectFilter subjectFilter = new SubjectFilter();
            subjectFilter.setSubject(criteria);
            return subjectFilter ;
        }
        else if(type.equals("Sender"))
        {
            SenderFilter senderFilter=new SenderFilter();
            senderFilter.setSender(criteria);
            return senderFilter;
        }
        else
        {
            PriorityFilter priorityFilter=new PriorityFilter();
            priorityFilter.setPriority(Integer.parseInt(criteria));
            return priorityFilter;
        }
    }
}
