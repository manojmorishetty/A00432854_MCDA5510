using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data_Access
{
    public class PersonController
    {
        PersonEntities personEntityInstance = new PersonEntities();
        public List<person> getAllPersons()
        {
            return personEntityInstance.people.ToList();
        }
    }
}
