#!/usr/bin/python

from optparse import OptionParser, Option, OptionValueError
import sys, os
import timeit, time
import re
from os.path import dirname
from Bio import SeqIO

def parse():
    
    lots = ['JacobsA', 'JacobsB', 'JacobsC', 'JarvisA', 'JarvisB', 'JarvisC', \
        'BairdA', 'BairdB', 'CookeA', 'CookeB', 'Furnas', 'FronczakA', 'FronczakB', \
        'GovernorsA', 'GovernorsB', 'GovernorsC', 'GovernorsD', 'GovernorsE', \
        'HochstetterA', 'HochstetterB', 'SleeA', 'SleeB', 'SpecialEvents']
    months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', \
        'August', 'September', 'October', 'November', 'December']
    days = ['Monday', 'Tuesday', 'Wednesday', 'Thurdsay', 'Friday', 'Saturday', 'Sunday']
    time =[]

    for l in lots:
        lotFile = open(l, ".txt", 'w+')
        for m in months:
            for d in days:
                for t in time:
                    lotFile.write(str(m) + '\t' + str(d) + '\t' + str(t))

def main():
    '''
    Main
    '''
    parse()
if __name__ == "__main__":
    main()
