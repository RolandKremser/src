##############################################################################
#
# (c) Copyright 1997, KL GROUP INC.  
# ALL RIGHTS RESERVED 
#
#  THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED
# AND COPIED ONLY IN ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND
# WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE.  THIS SOFTWARE OR
# ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE
# AVAILABLE TO ANY OTHER PERSON.  NO TITLE TO AND OWNERSHIP OF THE
# SOFTWARE IS HEREBY TRANSFERRED.
#
#  THE INFORMATION IN THIS SOFTWARE IS SUBJECT TO CHANGE WITHOUT
# NOTICE AND SHOULD NOT BE CONSTRUED AS A COMMITMENT BY KL GROUP INC.
# OR ITS THIRD PARTY SUPPLIERS.
#
#  KL GROUP INC. AND ITS THIRD PARTY SUPPLIERS, ASSUME NO RESPONSIBILITY 
# FOR THE USE OR INABILITY TO USE ANY OF ITS SOFTWARE.   THIS SOFTWARE IS 
# PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, AND KL GROUP INC. EXPRESSLY 
# DISCLAIMS ALL IMPLIED WARRANTIES, INCLUDING BUT NOT LIMITED TO THE 
# IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
#
# Notice:  Notwithstanding any other lease or license that may pertain to,
# or accompany the delivery of, this computer software, the rights of the
# Government regarding its use, reproduction and disclosure are as set
# forth in Section 52.227-19 of the FARS Computer Software-Restricted
# Rights clause.
#
# (c) Copyright 1997 KL Group Inc.  Unpublished - all
# rights reserved under the Copyright laws of the United States.
#
# RESTRICTED RIGHTS NOTICE:  Use, duplication, or disclosure by the
# Government is subject to the restrictions as set forth in subparagraph
# (c)(1)(ii) of the Rights in Technical Data and Computer Software clause
# at DFARS 52.227-7013.
#
# KL Group Inc.
# 260 King Street East
# Suite 300
# Toronto, Ontario
# Canada M5A 1K3
# (416) 594-1026
# (416) 594-1919 (FAX)
#
# RESTRICTED RIGHTS LEGEND:  This computer software is submitted with
# "restricted rights."  Use, duplication or disclosure is subject to the
# restrictions as set forth in NASA FAR SUP 18-52.227-79 (April 1985)
# "Commercial Computer Software- Restricted Rights (April 1985)."  KL Group
# Inc., 260 King St. E., Toronto, Ontario, Canada M5A 1K3.  If
# the contract contains the Clause at 18-52.227-74 "Rights in Data General"
# then the "Alternate III" clause applies.
#
# (c) Copyright 1997 KL Group Inc.
# ALL RIGHTS RESERVED
#
##############################################################################
# RCSID -- $RCSfile: makefile $ $Revision: 1.2 $ $Date: 2000/11/09 20:12:08 $ $Locker: $  KL Group Inc.

SRC =  \
LocaleInfo.java \
LocaleInfo_de.java \
LocaleInfo_en.java \
LocaleInfo_es.java \
LocaleInfo_fr.java

CLASSES = $(SRC:.java=.class)

JAVAC=javac 
JAVA=java
PACKAGE=
all: $(CLASSES)

tags:
	etags $(SRC)

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class
